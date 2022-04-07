package vttp2022.paf.day14.controllers;

import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.day14.models.LineItem;
import vttp2022.paf.day14.models.PurchaseOrder;
import vttp2022.paf.day14.services.PurchaseOrderService;

@Controller
@RequestMapping("/purchase_order")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService poSvc;

    @PostMapping
    public ModelAndView postPurchaseOrder(
        @RequestBody MultiValueMap<String, String> payload) {

        ModelAndView mvc = new ModelAndView();

        Optional<PurchaseOrder> optPO = create(payload);
        if (optPO.isEmpty()) {
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("error");
            return mvc;
        } 

        PurchaseOrder po = optPO.get();

        System.out.printf(">>> line items = %d\n", po.getLineItems().size());

        Optional<String> optOrderId = poSvc.createPurchaseOrder(po);
        if (optOrderId.isEmpty()) {
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("error");
            return mvc;
        }

        mvc.addObject("orderId", optOrderId.get());

        mvc.setStatus(HttpStatus.CREATED);
        mvc.setViewName("created");
        return mvc;
    } 

    private Optional<PurchaseOrder> create(MultiValueMap<String, String> payload) {
        PurchaseOrder po = new PurchaseOrder();

        po.setName(payload.getFirst("name"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            po.setDate(sdf.parse(payload.getFirst("date")));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }

        int i = 0;
        while (true) {
            String _prodId = payload.getFirst("prod_id-%d".formatted(i));
            if ((null == _prodId) || (0 == _prodId.trim().length()))
                break;
            String _qty = payload.getFirst("qty-%d".formatted(0));
            Integer productId = Integer.parseInt(_prodId);
            Integer quantity = Integer.parseInt(_qty);
            LineItem lineItem = new LineItem();
            lineItem.setProductId(productId);
            lineItem.setQuantity(quantity);
            po.addLineItem(lineItem);
            i++;
        }

        return Optional.of(po);
    }
    
}
