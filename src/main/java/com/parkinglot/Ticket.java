package com.parkinglot;

import java.util.Objects;

public class Ticket {
    private static int currentAvailableTicketId = 0;
    private Integer ticketId;

    public Ticket() {
        this.ticketId = currentAvailableTicketId++;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ticketId);
    }
}
