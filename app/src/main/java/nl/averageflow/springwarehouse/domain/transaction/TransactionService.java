package nl.averageflow.springwarehouse.domain.transaction;

import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import nl.averageflow.springwarehouse.domain.product.model.Product;
import nl.averageflow.springwarehouse.domain.product.repository.ProductRepository;
import nl.averageflow.springwarehouse.domain.transaction.model.Transaction;
import nl.averageflow.springwarehouse.domain.transaction.model.TransactionProduct;
import nl.averageflow.springwarehouse.domain.transaction.repository.TransactionProductRepository;
import nl.averageflow.springwarehouse.domain.transaction.repository.TransactionRepository;
import nl.averageflow.springwarehouse.domain.user.UserResponseItem;
import nl.averageflow.springwarehouse.domain.user.model.User;
import nl.averageflow.springwarehouse.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

    private final UserRepository userRepository;

    public TransactionService(
            final TransactionRepository transactionRepository,
            final TransactionProductRepository transactionProductRepository,
            final ProductRepository productRepository,
            final UserRepository userRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Page<TransactionResponseItem> getTransactions(final Pageable pageable) {
        final Page<Transaction> page = this.transactionRepository.findAll(pageable);

        return page.map(transaction -> new TransactionResponseItem(
                transaction.getUid(),
                new UserResponseItem(
                        transaction.getUser().getUid(),
                        transaction.getUser().getItemName(),
                        transaction.getUser().getEmail(),
                        transaction.getUser().getRole().getItemName(),
                        transaction.getUser().getCreatedAt(),
                        transaction.getUser().getUpdatedAt()
                ),
                transaction.getCreatedAt()
        ));
    }

    public TransactionResponseItem getTransactionByUid(final UUID uid) {
        final Transaction transaction = this.transactionRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find transaction with wanted UUID"));

        return new TransactionResponseItem(
                transaction.getUid(),
                new UserResponseItem(
                        transaction.getUser().getUid(),
                        transaction.getUser().getItemName(),
                        transaction.getUser().getEmail(),
                        transaction.getUser().getRole().getItemName(),
                        transaction.getUser().getCreatedAt(),
                        transaction.getUser().getUpdatedAt()
                ),

                transaction.getCreatedAt()
        );
    }

    public TransactionResponseItem createTransaction(final SellProductsRequest request, final Authentication authentication) {

        final User currentUser = this.userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find valid user to perform transaction with"));


        final Transaction transaction = new Transaction(currentUser);

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
                .map(item -> new TransactionProduct(
                        transaction,
                        item,
                        wantedProductAmounts.get(item.getUid()))
                ).collect(Collectors.toSet());

        transaction.setTransactionProducts(transactionProducts);

        final Transaction updatedTransaction = this.transactionRepository.save(transaction);

        this.transactionProductRepository.saveAll(transactionProducts);

        return new TransactionResponseItem(
                updatedTransaction.getUid(),
                new UserResponseItem(
                        transaction.getUser().getUid(),
                        transaction.getUser().getItemName(),
                        transaction.getUser().getEmail(),
                        transaction.getUser().getRole().getItemName(),
                        transaction.getUser().getCreatedAt(),
                        transaction.getUser().getUpdatedAt()
                ),
                updatedTransaction.getCreatedAt()
        );
    }
}
