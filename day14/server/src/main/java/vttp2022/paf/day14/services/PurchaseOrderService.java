package vttp2022.paf.day14.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.day14.models.PurchaseOrder;
import vttp2022.paf.day14.respositories.LineItemRepository;
import vttp2022.paf.day14.respositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private LineItemRepository liRepo; 

    public Optional<String> createPurchaseOrder(PurchaseOrder po) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        po.setOrderId(orderId);

        if (poRepo.insertPurchaseOrder(po) 
                && liRepo.insertLineItems(orderId, po.getLineItems()))
            return Optional.of(orderId);

        return Optional.empty();
    }
}
