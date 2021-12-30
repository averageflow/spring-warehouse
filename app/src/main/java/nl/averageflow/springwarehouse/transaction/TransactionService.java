package nl.averageflow.springwarehouse.transaction;

import nl.averageflow.springwarehouse.product.model.Product;
import nl.averageflow.springwarehouse.transaction.model.Transaction;
import nl.averageflow.springwarehouse.transaction.model.TransactionProduct;
import nl.averageflow.springwarehouse.product.repository.ProductRepository;
import nl.averageflow.springwarehouse.transaction.repository.TransactionProductRepository;
import nl.averageflow.springwarehouse.transaction.repository.TransactionRepository;
import nl.averageflow.springwarehouse.product.dto.SellProductsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionService implements TransactionServiceContract {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionProductRepository transactionProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<Transaction> getTransactions(final Pageable pageable) {
        return this.transactionRepository.findAll(pageable);
    }

    public Optional<Transaction> getTransactionByUid(final UUID uid) {
        return this.transactionRepository.findByUid(uid);
    }

    public Transaction createTransaction(final SellProductsRequest request) {
        final Transaction transaction = new Transaction();

        final HashMap<UUID, Long> wantedProductAmounts = new HashMap<>();
        final Collection<UUID> wantedProductUUIDs = new ArrayList<>();

        StreamSupport.stream(request.wantedItemsForSale().spliterator(), false)
                .forEach(item -> {
                    wantedProductAmounts.put(item.itemUid(), item.amountOf());
                    wantedProductUUIDs.add(item.itemUid());
                });

        final Iterable<Product> wantedProducts = this.productRepository.findAllById(wantedProductUUIDs);

        if (StreamSupport.stream(wantedProducts.spliterator(), false).count() != wantedProductUUIDs.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find wanted item to perform sale operation on");
        }

        final Set<TransactionProduct> transactionProducts = StreamSupport.stream(wantedProducts.spliterator(), false)
                .map(item -> new TransactionProduct(transaction, item, wantedProductAmounts.get(item.getUid())))
                .collect(Collectors.toSet());

        transaction.setTransactionProducts(transactionProducts);


        final Transaction updatedTransaction = this.transactionRepository.save(transaction);

        this.transactionProductRepository.saveAll(transactionProducts);

        return updatedTransaction;
    }
}
