package vincentlow.parkee.parkingpos.service;

import vincentlow.parkee.parkingpos.model.entity.Member;

public interface MemberService {

  Member getMemberDetail(String plateNumber);
}
