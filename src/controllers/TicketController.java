package controllers;

import dtos.IssueTicketRequest;
import dtos.IssueTicketResponse;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    public IssueTicketResponse issueTicket(IssueTicketRequest issueTicketRequest) {
        return new IssueTicketResponse();
    }
}
