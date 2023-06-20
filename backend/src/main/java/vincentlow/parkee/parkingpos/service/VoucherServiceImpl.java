package vincentlow.parkee.parkingpos.service;

import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.entity.Voucher;
import vincentlow.parkee.parkingpos.repository.VoucherRepository;

@Service
public class VoucherServiceImpl implements VoucherService {

  @Autowired
  private VoucherRepository voucherRepository;

  @Override
  public Voucher findValidVoucherById(String id) {

    validateRequest(StringUtils.isNotBlank(id), ErrorMessage.VOUCHER_ID_MUST_NOT_BE_BLANK);
    return voucherRepository.findValidVoucherByIdAndMarkForDeleteFalse(id);
  }
}
