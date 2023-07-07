package org.pahappa.systems.ticketing.views;

import org.pahappa.systems.ticketing.constants.TicketStatus;
import org.pahappa.systems.ticketing.models.Ticket;
import org.pahappa.systems.ticketing.services.TicketService;
import org.pahappa.systems.ticketing.services.impl.TicketServiceImpl;

import java.util.List;
import java.util.Scanner;

public class TicketView implements BaseTicketView {

    private final TicketService ticketService;
    private final Scanner scanner;

    public TicketView() {
        this.ticketService = new TicketServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("********* Call Center Ticket System *********\n\n");
        boolean running = true;
        while (running) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Ticket");
            System.out.println("2. Get All Tickets");
            System.out.println("3. Get Tickets of Status");
            System.out.println("4. Update Ticket");
            System.out.println("5. Delete Ticket");
            System.out.println("6. Exit");
            System.out.println();

            int choice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Please choose the operation to be executed: ");
                if (scanner.hasNextInt()) { //checking if the input is an integer and can be consumed
                    choice = scanner.nextInt(); //consuming the integer entered by the user
                    validChoice = true;
                } else {
                    System.out.println("Invalid input. Please enter a number that matches the operation you have chosen.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createTicket();
                    break;
                case 2:
                    getAllTickets();
                    break;
                case 3:
                    getTicketsOfStatus();
                    break;
                case 4:
                    updateTicket();
                    break;
                case 5:
                    deleteTicket();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }

    @Override
    public void createTicket() {
        Ticket ticket = new Ticket();

        System.out.println("Enter the ticket category:");
        String category = scanner.nextLine();
        ticket.setCategory(category);

        System.out.println("Enter the customer name:");
        String customer = scanner.nextLine();
        ticket.setCustomer(customer);

        System.out.println("Enter the issue description:");
        String issue = scanner.nextLine();
        ticket.setIssue(issue);

        boolean validStatus = false;
        while (!validStatus) {
            System.out.println("Enter the ticket status (OPEN, INPROGRESS, or RESOLVED):");
            String statusInput = scanner.nextLine();

            try {
                TicketStatus status = TicketStatus.valueOf(statusInput.toUpperCase());
                ticket.setStatus(status.name());
                validStatus = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please try again.");
            }
        }

        System.out.println("Enter the priority level:");
        String priorityLevel = scanner.nextLine();
        ticket.setPriorityLevel(priorityLevel);

        ticketService.createTicket(ticket);
    }

    @Override
    public void getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();

        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            System.out.println("All Tickets:");
            for (Ticket ticket : tickets) {
                System.out.println("Ticket ID: " + ticket.getId());
                System.out.println("Category: " + ticket.getCategory());
                System.out.println("Customer: " + ticket.getCustomer());
                System.out.println("Issue: " + ticket.getIssue());
                System.out.println("Status: " + ticket.getStatus());
                System.out.println("Priority Level: " + ticket.getPriorityLevel());
                System.out.println("--------------------------");
            }
        }
    }

    @Override
    public void getTicketsOfStatus() {
        boolean validStatus = false;
        TicketStatus status = null;
        while (!validStatus) {
            System.out.println("Enter the ticket status (OPEN, INPROGRESS, or RESOLVED):");
            String statusInput = scanner.nextLine();

            try {
                status = TicketStatus.valueOf(statusInput.toUpperCase());
                validStatus = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please try again.");
            }
        }

        List<Ticket> tickets = ticketService.getTicketsOfStatus(status);

        if (tickets.isEmpty()) {
            System.out.println("No tickets found with the specified status.");
        } else {
            System.out.println("Tickets with status " + status.name() + ":");
            for (Ticket ticket : tickets) {
                System.out.println("Ticket ID: " + ticket.getId());
                System.out.println("Category: " + ticket.getCategory());
                System.out.println("Customer: " + ticket.getCustomer());
                System.out.println("Issue: " + ticket.getIssue());
                System.out.println("Status: " + ticket.getStatus());
                System.out.println("Priority Level: " + ticket.getPriorityLevel());
                System.out.println("--------------------------");
            }
        }
    }


    @Override
    public void updateTicket() {
        System.out.println("Enter the ID of the ticket you want to update:");
        long ticketId = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        Ticket ticketToUpdate = null;
        for (Ticket ticket : ticketService.getAllTickets()) {
            if (ticket.getId() == ticketId) {
                ticketToUpdate = ticket;
                break;
            }
        }

        if (ticketToUpdate == null) {
            System.out.println("Ticket not found.");
            return;
        }

        System.out.println("Enter the updated ticket category:");
        String category = scanner.nextLine();
        ticketToUpdate.setCategory(category);

        System.out.println("Enter the updated customer name:");
        String customer = scanner.nextLine();
        ticketToUpdate.setCustomer(customer);

        System.out.println("Enter the updated issue description:");
        String issue = scanner.nextLine();
        ticketToUpdate.setIssue(issue);

        boolean validStatus = false;
        while (!validStatus) {
            System.out.println("Enter the ticket status (OPEN, INPROGRESS, or RESOLVED):");
            String statusInput = scanner.nextLine();

            try {
                TicketStatus status = TicketStatus.valueOf(statusInput.toUpperCase());
                ticketToUpdate.setStatus(status.name());
                validStatus = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please try again.");
            }
        }

        System.out.println("Enter the updated priority level:");
        String priorityLevel = scanner.nextLine();
        ticketToUpdate.setPriorityLevel(priorityLevel);

        ticketService.updateTicket(ticketToUpdate);
    }


    @Override
    public void deleteTicket() {
        System.out.println("Enter the ID of the ticket you want to delete:");
        int ticketId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        ticketService.deleteTicket(ticketId);
    }
}
