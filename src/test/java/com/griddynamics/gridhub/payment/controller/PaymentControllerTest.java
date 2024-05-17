package com.griddynamics.gridhub.payment.controller;

import com.griddynamics.gridhub.payment.dto.PaymentMethodDto;
import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.service.PaymentService;
import com.griddynamics.gridhub.payment.service.implementation.ServiceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PaymentControllerTest {

  @Mock private ServiceFactoryImpl serviceFactory;
  @Mock private PaymentService<PaymentMethodDto> paymentService;

  private PaymentController controller;

  @BeforeEach
  public void setUp() {
    controller = new PaymentController(serviceFactory);
    when(serviceFactory.getService(any(PaymentType.class))).thenReturn(paymentService);
  }

  @Test
  public void testSave() {
    PaymentMethodDto dto = mock(PaymentMethodDto.class);
    when(dto.getPaymentType()).thenReturn(PaymentType.PAYPAL);
    when(paymentService.save(anyLong(), any())).thenReturn(dto);

    PaymentMethodDto result = controller.save(1L, dto);
    verify(paymentService).save(1L, dto);
    assertEquals(dto, result);
  }

  @Test
  public void testDelete() {
    controller.delete(PaymentType.PAYPAL, 1L);
    verify(paymentService).delete(1L);
  }

  @Test
  public void testUpdate() {
    PaymentMethodDto dto = mock(PaymentMethodDto.class);
    when(dto.getPaymentType()).thenReturn(PaymentType.CREDIT_CARD);
    when(paymentService.update(anyLong(), anyLong(), any())).thenReturn(dto);

    PaymentMethodDto result = controller.update(1L, 2L, dto);
    verify(paymentService).update(1L, 2L, dto);
    assertEquals(dto, result);
  }
}
