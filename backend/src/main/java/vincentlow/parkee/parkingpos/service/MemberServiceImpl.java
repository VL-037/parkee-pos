package vincentlow.parkee.parkingpos.service;

import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateMember;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateRequest;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.entity.Member;
import vincentlow.parkee.parkingpos.model.request.GetMemberDetailRequest;
import vincentlow.parkee.parkingpos.repository.MemberRepository;
import vincentlow.parkee.parkingpos.util.StringUtil;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public Member getMemberDetail(GetMemberDetailRequest request) {

    validateRequest(Objects.nonNull(request), ErrorMessage.REQUEST_MUST_NOT_BE_NULL);
    StringUtil.trimStrings(request);
    validateRequest(StringUtils.isNotBlank(request.getPlateNumber()), ErrorMessage.PLATE_NUMBER_MUST_NOT_BE_BLANK);

    return validateMember(memberRepository.findByPlateNumberAndMarkForDeleteFalse(request.getPlateNumber()));
  }
}
