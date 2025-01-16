	package com.example.api.service;
	
	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.Invoice;
import com.example.api.repository.InvoiceRepository;
	
	@Service
	public class InvoiceService {
	    @Autowired
	    private InvoiceRepository invoiceRepository;
	
	    // Lấy danh sách tất cả hóa đơn
	    public List<Invoice> getAllInvoices() {
	        return invoiceRepository.findAll();
	    }
	
	    // Lấy hóa đơn theo ID
	    public Invoice getInvoiceById(Long id) {
	        return invoiceRepository.findById(id).orElse(null);
	    }
	
	    // Tạo hóa đơn mới
	    public Invoice createInvoice(Invoice invoice) {
	        return invoiceRepository.save(invoice);
	    }
	
	    // Cập nhật thông tin hóa đơn
	    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
	        Invoice invoice = invoiceRepository.findById(id).orElse(null);
	        if (invoice != null) {
	            // Cập nhật các trường
	            invoice.setTotalAmount(updatedInvoice.getTotalAmount());
	            invoice.setPaymentStatus(updatedInvoice.getPaymentStatus());
	            return invoiceRepository.save(invoice);
	        } else {
	            return null;
	        }
	    }
	
	    // Xóa hóa đơn
	    public void deleteInvoice(Long id) {
	        invoiceRepository.deleteById(id);
	    }
	}