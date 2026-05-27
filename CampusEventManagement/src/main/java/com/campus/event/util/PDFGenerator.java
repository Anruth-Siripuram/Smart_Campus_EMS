package com.campus.event.util;

import com.campus.event.model.Booking;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;

import java.io.ByteArrayOutputStream;

public class PDFGenerator {

    public static byte[] generateTicket(Booking b) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        doc.add(new Paragraph("EVENT TICKET").setBold().setFontSize(18));

        doc.add(new Paragraph("Ticket ID: " + b.getTicketId()));
        doc.add(new Paragraph("Event: " + b.getEvent().getName()));
        doc.add(new Paragraph("Venue: " + b.getEvent().getVenue()));
        doc.add(new Paragraph("Date: " + b.getEvent().getDateTime()));
        doc.add(new Paragraph("Tickets: " + b.getTicketsCount()));
        doc.add(new Paragraph("Paid: ₹" + b.getTotalPrice()));

        doc.close();

        return out.toByteArray();
    }
}