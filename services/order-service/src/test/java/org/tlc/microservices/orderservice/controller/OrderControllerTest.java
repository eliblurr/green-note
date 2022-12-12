//package org.tlc.microservices.orderservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.tlc.domain.base.order.enums.OrderPosition;
//import org.tlc.domain.base.order.enums.Side;
//import org.tlc.domain.base.order.Response;
//import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
//import org.tlc.domain.base.order.enums.OrderType;
//import org.tlc.microservices.orderservice.services.OrderService;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//
//
////@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class OrderControllerTest {
//    @MockBean
//    private OrderService orderService;
//    @Autowired
//    private MockMvc mockMvc;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//
//
//
//    @Test
//    public void makeOrder_WithValidRequestTest() throws Exception{
//        //given a valid order response
//        Response expectedResponse = new Response(true, "Order placed successfully");
//        OrderRequestDTO order = new OrderRequestDTO(1,"IBM",1.65,20,1,Side.BUY,OrderPosition.NORMAL,OrderType.LIMIT);
//        doReturn(expectedResponse).when(orderService).placeOrder(any());
//        //when the order request is passed to the orderService
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/books")
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(order))
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result =  mockMvc.perform(requestBuilder).andReturn();
//        String content = result.getResponse().getContentAsString();
//
//        Assertions.assertEquals(expectedResponse.getMessage(),content);
//        //when the orderController receives the order
//
//
//        //then verify that orderService's placeOrder method was called once
////        Assertions.assertEquals(expectedResponse, result);
//    }
//
//
//}