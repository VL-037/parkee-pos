package vincentlow.parkee.parkingpos.service;

import vincentlow.parkee.parkingpos.model.entity.Member;
import vincentlow.parkee.parkingpos.model.request.GetMemberDetailRequest;

public interface MemberService {

  Member getMemberDetail(GetMemberDetailRequest request);
}
