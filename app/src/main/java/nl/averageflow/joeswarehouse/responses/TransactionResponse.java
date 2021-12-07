package nl.averageflow.joeswarehouse.responses;

import nl.averageflow.joeswarehouse.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public final class TransactionResponse {
    private HashMap<UUID, Transaction> data;

    private Iterable<UUID> sort;

    public TransactionResponse(Set<Transaction> data) {
        HashMap<UUID, Transaction> dataMap = new HashMap<>();
        ArrayList<UUID> dataSort = new ArrayList<>();

        for (Transaction transaction : data) {
            dataMap.put(transaction.getUid(), transaction);
            dataSort.add(transaction.getUid());
        }

        this.setData(dataMap);
        this.setSort(dataSort);
    }

    public HashMap<UUID, Transaction> getData() {
        return this.data;
    }

    public void setData(HashMap<UUID, Transaction> data) {
        this.data = data;
    }

    public Iterable<UUID> getSort() {
        return this.sort;
    }

    public void setSort(Iterable<UUID> sort) {
        this.sort = sort;
    }
}
