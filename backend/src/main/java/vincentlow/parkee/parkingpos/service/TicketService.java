package vincentlow.parkee.parkingpos.service;

import vincentlow.parkee.parkingpos.model.entity.CheckInTicket;
import vincentlow.parkee.parkingpos.model.entity.CheckOutTicket;
import vincentlow.parkee.parkingpos.model.request.CreateCheckInTicketRequest;
import vincentlow.parkee.parkingpos.model.request.CreateCheckOutTicketRequest;
import vincentlow.parkee.parkingpos.model.request.GetCheckoutTicketDetailRequest;

public interface TicketService {

  CheckInTicket createCheckInTicket(CreateCheckInTicketRequest request);

  CheckInTicket getCheckInTicketDetail(GetCheckoutTicketDetailRequest request);

  CheckOutTicket createCheckOutTicket(CreateCheckOutTicketRequest request);
}
