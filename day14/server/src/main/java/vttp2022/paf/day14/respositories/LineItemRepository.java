package vttp2022.paf.day14.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.day14.models.LineItem;

import static vttp2022.paf.day14.respositories.Queries.*;

import java.util.List;

@Repository
public class LineItemRepository {

    @Autowired
    private JdbcTemplate liRepo;

    public boolean insertLineItem(String orderId, LineItem li) {
        int count = liRepo.update(SQL_INSERT_LINE_ITEM,
            li.getProductId(), orderId, li.getQuantity());
        return 1 == count;
    }

    public boolean insertLineItems(String orderId, List<LineItem> lineItems) {
        for (LineItem li: lineItems)
            if (!insertLineItem(orderId, li))
                return false;
        return true;
    }
    
}
