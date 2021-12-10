package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.Product;
import nl.averageflow.springwarehouse.models.Transaction;
import nl.averageflow.springwarehouse.models.TransactionProduct;
import nl.averageflow.springwarehouse.repositories.ProductRepository;
import nl.averageflow.springwarehouse.repositories.TransactionProductRepository;
import nl.averageflow.springwarehouse.repositories.TransactionRepository;
import nl.averageflow.springwarehouse.requests.SellProductsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public final class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionProductRepository transactionProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<Transaction> getTransactions(Pageable pageable) {
        return this.transactionRepository.findAll(pageable);
    }

    public Optional<Transaction> getTransactionByUid(UUID uid) {
        return this.transactionRepository.findByUid(uid);
    }

    public Transaction createTransaction(SellProductsRequest request){
        Transaction transaction = new Transaction();

        HashMap<UUID, Long> wantedProductAmounts = new HashMap<>();
        List<UUID> wantedProductUUIDs = new ArrayList<>();
        StreamSupport.stream(request.getWantedItemsForSale().spliterator(), false)
                .forEach(item -> {
                    wantedProductAmounts.put(item.getItemUid(), item.getAmountOf());
                    wantedProductUUIDs.add(item.getItemUid());
                });

        Iterable<Product> wantedProducts = this.productRepository.findAllById(wantedProductUUIDs);

        if(StreamSupport.stream(wantedProducts.spliterator(), false).count() != wantedProductUUIDs.size()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find wanted item to perform sale operation on");
        }

        Set<TransactionProduct> transactionProducts = StreamSupport.stream(wantedProducts.spliterator(), false)
                        .map(item -> new TransactionProduct(transaction, item, wantedProductAmounts.get(item.getUid())))
                .collect(Collectors.toSet());

        transaction.setTransactionProducts(transactionProducts);


        Transaction updatedTransaction = this.transactionRepository.save(transaction);

        this.transactionProductRepository.saveAll(transactionProducts);

        return updatedTransaction;
    }
}
