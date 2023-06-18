package vincentlow.parkee.parkingpos.service;

import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateOfficer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.entity.Officer;
import vincentlow.parkee.parkingpos.repository.OfficerRepository;
import vincentlow.parkee.parkingpos.util.StringUtil;
import vincentlow.parkee.parkingpos.util.ValidatorUtil;

@Service
public class OfficerServiceImpl implements OfficerService {

  @Autowired
  private OfficerRepository officerRepository;

  @Override
  public Officer getOfficerDetail(String id) {

    StringUtil.trimStrings(id);
    ValidatorUtil.validateRequest(StringUtils.isNotBlank(id), ErrorMessage.OFFICER_ID_MUST_NOT_BE_BLANK);

    return validateOfficer(officerRepository.findByIdAndMarkForDeleteFalse(id));
  }
}
