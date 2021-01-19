package backend.controller;

import backend.service.BillServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping( path = "/bill" )
public class BillController {
    @Autowired
    private BillServiceImpl billService;
}
