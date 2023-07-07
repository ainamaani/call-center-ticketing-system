package org.pahappa.systems.ticketing.views;

import org.pahappa.systems.ticketing.constants.TicketStatus;
import org.pahappa.systems.ticketing.models.Ticket;
import org.pahappa.systems.ticketing.services.TicketService;
import org.pahappa.systems.ticketing.services.impl.TicketServiceImpl;

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

            int choice = scanner.nextInt();
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

    }

    @Override
    public void getTicketsOfStatus() {

    }

    @Override
    public void updateTicket() {

    }

    @Override
    public void deleteTicket() {

    }
}
