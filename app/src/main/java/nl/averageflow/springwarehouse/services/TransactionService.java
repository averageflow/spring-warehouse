package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.Product;
import nl.averageflow.springwarehouse.models.Transaction;
import nl.averageflow.springwarehouse.models.TransactionProduct;
import nl.averageflow.springwarehouse.repositories.ProductRepository;
import nl.averageflow.springwarehouse.repositories.TransactionRepository;
import nl.averageflow.springwarehouse.requests.SellProductsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public final class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

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

        Set<TransactionProduct> transactionProducts = StreamSupport.stream(wantedProducts.spliterator(), false)
                        .map(item -> new TransactionProduct(transaction, item, wantedProductAmounts.get(item.getUid())))
                .collect(Collectors.toSet());

        transaction.setTransactionProducts(transactionProducts);

        return this.transactionRepository.save(transaction);
    }
}
