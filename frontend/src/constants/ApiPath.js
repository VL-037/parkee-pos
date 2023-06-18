const BASE_PATH = "api/v1";

const ApiPath = {
  Officer: `${BASE_PATH}/officers`,
  Member: `${BASE_PATH}/members`,
  Parking: `${BASE_PATH}/parkings`,
  Ticket: `${BASE_PATH}/tickets`,
  TicketCheckIn: `${BASE_PATH}/tickets/check-in`,
  TicketCheckOut: `${BASE_PATH}/tickets/check-out`,
};

export default ApiPath;
