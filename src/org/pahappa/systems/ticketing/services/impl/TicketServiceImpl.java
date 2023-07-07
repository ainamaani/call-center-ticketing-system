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
        List<Ticket> filteredTickets = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if (ticket.getStatus().equals(ticketStatus.name())) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }

    @Override
    public void updateTicket(Ticket updatedTicket) {
        for (Ticket ticket : ticketList) {
            if (ticket.getId() == updatedTicket.getId()) {
                ticket.setCategory(updatedTicket.getCategory());
                ticket.setCustomer(updatedTicket.getCustomer());
                ticket.setIssue(updatedTicket.getIssue());
                ticket.setStatus(updatedTicket.getStatus());
                ticket.setPriorityLevel(updatedTicket.getPriorityLevel());
                System.out.println("Ticket updated successfully.");
                return;
            }
        }
        System.out.println("Ticket not found.");
    }
    @Override
    public void deleteTicket(int index) {

    }
}