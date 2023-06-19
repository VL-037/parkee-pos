package vincentlow.parkee.parkingpos.service;

import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateMember;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.entity.Member;
import vincentlow.parkee.parkingpos.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public Member getMemberDetail(String plateNumber) {

    validateRequest(StringUtils.isNotBlank(plateNumber), ErrorMessage.PLATE_NUMBER_MUST_NOT_BE_BLANK);
    return validateMember(memberRepository.findByPlateNumberAndMarkForDeleteFalse(plateNumber));
  }
}
