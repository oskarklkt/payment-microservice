package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.exception.PaypalException;
import com.griddynamics.gridhub.payment.mapper.dtoToModel.PaypalMapper;
import com.griddynamics.gridhub.payment.mapper.modelToDto.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.model.Paypal;
import com.griddynamics.gridhub.payment.repository.PaypalRepository;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
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
public class PaypalServiceTest {

    @Mock
    private PaypalRepository paypalRepository;
    @Mock
    private PaypalDtoMapper paypalDtoMapper;
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private PaypalMapper paypalMapper;
    @Mock
    private PaypalDto paypalDto;
    @Mock
    private Paypal paypal;

    private PaypalService paypalService;

    @BeforeEach
    public void setUp() {
        paypalService = new PaypalService(paypalRepository, paypalDtoMapper, validationUtil, paypalMapper);
        lenient().when(paypalMapper.apply(anyLong(), anyLong(), any())).thenReturn(paypal);
        lenient().when(paypalDtoMapper.apply(any())).thenReturn(paypalDto);
    }

    @Test
    public void testSave() {
        when(validationUtil.validatePaypal(any())).thenReturn(true);
        PaypalDto result = paypalService.save(1L, paypalDto);

        verify(paypalRepository, times(1)).save(paypal);
        assertEquals(paypalDto, result);
    }

    @Test
    public void testSaveInvalidPaypal() {
        when(validationUtil.validatePaypal(paypalDto)).thenReturn(false);

        assertThrows(PaypalException.class, () -> paypalService.save(1L, paypalDto));
    }

    @Test
    public void testDelete() {
        when(paypalRepository.isContains(1L)).thenReturn(false);

        paypalService.delete(1L);

        verify(paypalRepository, times(1)).delete(1L);
    }

    @Test
    public void testDeleteNoSuchElement() {
        when(paypalRepository.isContains(1L)).thenReturn(true);

        assertThrows(NoSuchElementException.class, () -> paypalService.delete(1L));
    }

    @Test
    public void testUpdate() {
        when(validationUtil.validatePaypal(paypalDto)).thenReturn(true);
        when(paypalRepository.isContains(1L)).thenReturn(false);

        PaypalDto result = paypalService.update(1L, 1L, paypalDto);

        verify(paypalRepository, times(1)).update(paypal);
        assertEquals(paypalDto, result);
    }

    @Test
    public void testUpdateInvalidPaypal() {
        when(validationUtil.validatePaypal(paypalDto)).thenReturn(false);

        assertThrows(PaypalException.class, () -> paypalService.update(1L, 1L, paypalDto));
    }

    @Test
    public void testUpdateNoSuchElement() {
        when(paypalRepository.isContains(1L)).thenReturn(true);

        assertThrows(NoSuchElementException.class, () -> paypalService.update(1L, 1L, paypalDto));
    }

    @Test
    public void testGet() {
        List<Paypal> paypals = Collections.singletonList(paypal);
        when(paypalRepository.get(1L)).thenReturn(paypals);

        List<PaypalDto> result = paypalService.get(1L);

        verify(paypalRepository, times(1)).get(1L);
        assertEquals(1, result.size());
        assertEquals(paypalDto, result.get(0));
    }
}