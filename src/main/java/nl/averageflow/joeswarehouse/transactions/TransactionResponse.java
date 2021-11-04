package nl.averageflow.joeswarehouse.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TransactionResponse {
    private HashMap<Long, Transaction> data;

    private List<Long> sort;

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

    public TransactionResponse(Set<Transaction> data) {
        HashMap<Long, Transaction> dataMap = new HashMap<Long, Transaction>();
        List<Long> dataSort = new ArrayList<Long>();

        for (Transaction transaction : data) {
            dataMap.put(transaction.getId(), transaction);
            dataSort.add(transaction.getId());
        }

        this.setData(dataMap);
        this.setSort(dataSort);
    }
}
