package nl.averageflow.joeswarehouse.responses;

import nl.averageflow.joeswarehouse.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public final class TransactionResponse {
    private HashMap<Long, Transaction> data;

    private List<Long> sort;

    public TransactionResponse(Set<Transaction> data) {
        HashMap<Long, Transaction> dataMap = new HashMap<>();
        List<Long> dataSort = new ArrayList<>();

        for (Transaction transaction : data) {
            dataMap.put(transaction.getId(), transaction);
            dataSort.add(transaction.getId());
        }

        this.setData(dataMap);
        this.setSort(dataSort);
    }

    public HashMap<Long, Transaction> getData() {
        return this.data;
    }

    public void setData(HashMap<Long, Transaction> data) {
        this.data = data;
    }

    public List<Long> getSort() {
        return this.sort;
    }

    public void setSort(List<Long> sort) {
        this.sort = sort;
    }
}
