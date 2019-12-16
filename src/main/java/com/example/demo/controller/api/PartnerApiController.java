package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.network.response.*;
import com.example.demo.service.PartnerApiLogicService;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;
import com.example.demo.controller.CrudController;
import com.example.demo.controller.ifs.CrudInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse,Partner> {


}
