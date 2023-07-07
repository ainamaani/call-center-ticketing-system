package org.pahappa.systems.ticketing.services.impl;

import org.pahappa.systems.ticketing.constants.TicketStatus;
import org.pahappa.systems.ticketing.models.Ticket;
import org.pahappa.systems.ticketing.services.TicketService;

import java.util.ArrayList;
import java.util.List;


public class TicketServiceImpl implements TicketService {

    private final List<Ticket> ticketList;
    private long lastTicketId;

    public TicketServiceImpl() {
        this.ticketList = new ArrayList<>();
        this.lastTicketId = 0; // Initialize the last ticket ID
    }
    @Override
    public void createTicket(Ticket ticket) {
        // Increment the last ticket ID and assign it to the new ticket
        ticket.setId(++lastTicketId);
        ticketList.add(ticket);
        System.out.println("Ticket created successfully.");
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketList;
    }

    @Override
    public List<Ticket> getTicketsOfStatus(TicketStatus ticketStatus) {
        return null;
    }

    @Override
    public void updateTicket(Ticket updatedTicket) {

    }

    @Override
    public void deleteTicket(int index) {

    }
}