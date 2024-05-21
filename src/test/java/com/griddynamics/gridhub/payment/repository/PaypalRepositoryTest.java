package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.datasource.QueryHandler;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetPaypalMapper;
import com.griddynamics.gridhub.payment.model.Paypal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaypalRepositoryTest {

    @Mock
    private QueryHandler<Paypal> queryHandler;

    @Mock
    private ResultSetPaypalMapper resultSetPaypalMapper;
    @Mock
    private Paypal paypal;


    private PaypalRepository paypalRepository;

    @BeforeEach
    public void setUp() {
        paypalRepository = new PaypalRepository(queryHandler, resultSetPaypalMapper);
        paypal = Paypal.builder()
                .id(1L)
                .userId(1L)
                .paymentType(PaymentType.PAYPAL)
                .email("o@gmail.com")
                .build();
    }

    @Test
    public void testSave() {
        paypalRepository.save(paypal);

        verify(queryHandler, times(1)).execute(anyString(), anyLong(), anyLong(), anyString(), anyString());
    }

    @Test
    public void testUpdate() {
        paypalRepository.update(paypal);

        verify(queryHandler, times(1)).execute(anyString(), anyString(), anyLong());
    }

    @Test
    public void testDelete() {
        paypalRepository.delete(1L);

        verify(queryHandler, times(1)).execute(anyString(), anyLong());
    }



    @Test
    public void testGet() {
        List<Paypal> expected = Collections.singletonList(paypal);
        when(queryHandler.findMany(anyString(), any(), anyLong())).thenReturn(expected);

        List<Paypal> result = paypalRepository.get(1L);

        verify(queryHandler, times(1)).findMany(anyString(), any(), anyLong());
        assertEquals(expected, result);
    }

    @Test
    public void testIsContains() {
        when(queryHandler.findOne(anyString(), any(), anyLong())).thenReturn(paypal);

        boolean result = paypalRepository.isContains(1L);

        verify(queryHandler, times(1)).findOne(anyString(), any(), anyLong());
        assertTrue(result);
    }

    @Test
    public void testGetNextId() {
        when(queryHandler.findMany(anyString(), any())).thenReturn(Collections.singletonList(paypal));

        Long result = paypalRepository.getNextId();

        verify(queryHandler, times(1)).findMany(anyString(), any());
        assertEquals(2L, result);
    }
}
