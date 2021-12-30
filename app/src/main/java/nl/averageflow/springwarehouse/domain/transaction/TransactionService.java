package nl.averageflow.springwarehouse.domain.transaction;

import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import nl.averageflow.springwarehouse.domain.product.model.Product;
import nl.averageflow.springwarehouse.domain.product.repository.ProductRepository;
import nl.averageflow.springwarehouse.domain.transaction.model.Transaction;
import nl.averageflow.springwarehouse.domain.transaction.model.TransactionProduct;
import nl.averageflow.springwarehouse.domain.transaction.repository.TransactionProductRepository;
import nl.averageflow.springwarehouse.domain.transaction.repository.TransactionRepository;
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
    private final TransactionRepository transactionRepository;

    private final TransactionProductRepository transactionProductRepository;

    private final ProductRepository productRepository;

    public TransactionService(final TransactionRepository transactionRepository, final TransactionProductRepository transactionProductRepository, final ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
        this.productRepository = productRepository;
    }

    public Page<TransactionResponseItem> getTransactions(final Pageable pageable) {
        final Page<Transaction> page = this.transactionRepository.findAll(pageable);

        return page.map(transaction -> new TransactionResponseItem(
                transaction.getUid(),
                transaction.getCreatedAt()
        ));
    }

    public TransactionResponseItem getTransactionByUid(final UUID uid) {
        final Optional<Transaction> searchResult = this.transactionRepository.findByUid(uid);

        if (searchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find transaction with wanted UUID");
        }

        final Transaction transaction = searchResult.get();

        return new TransactionResponseItem(
                transaction.getUid(),
                transaction.getCreatedAt()
        );
    }

    public TransactionResponseItem createTransaction(final SellProductsRequest request) {
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

        return new TransactionResponseItem(
                updatedTransaction.getUid(),
                updatedTransaction.getCreatedAt()
        );
    }
}
