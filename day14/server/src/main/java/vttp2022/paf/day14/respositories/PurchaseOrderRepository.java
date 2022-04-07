package vttp2022.paf.day14.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.day14.models.PurchaseOrder;

import static vttp2022.paf.day14.respositories.Queries.*;

@Repository
public class PurchaseOrderRepository {

    @Autowired
    private JdbcTemplate template;

    public boolean insertPurchaseOrder(PurchaseOrder po) {

        int count = template.update(SQL_INSERT_PURCHASE_ORDER
            , po.getOrderId(), po.getName(), po.getDate());

        return 1 == count;
    }
    
}
