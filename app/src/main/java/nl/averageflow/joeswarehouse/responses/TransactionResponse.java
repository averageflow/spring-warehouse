package nl.averageflow.joeswarehouse.responses;

import nl.averageflow.joeswarehouse.models.Transaction;

import java.util.*;

public final class TransactionResponse {
    private HashMap<UUID, Transaction> data;

    private List<UUID> sort;

    public TransactionResponse(Set<Transaction> data) {
        HashMap<UUID, Transaction> dataMap = new HashMap<>();
        List<UUID> dataSort = new ArrayList<>();

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

    public List<UUID> getSort() {
        return this.sort;
    }

    public void setSort(List<UUID> sort) {
        this.sort = sort;
    }
}
