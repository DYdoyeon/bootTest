package com.example.demo.controller.api;

import com.example.demo.model.network.response.*;
import com.example.demo.model.entity.Category;
import com.example.demo.model.network.request.*;
import com.example.demo.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {

}
