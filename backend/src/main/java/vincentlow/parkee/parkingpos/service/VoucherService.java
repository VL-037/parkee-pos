package vincentlow.parkee.parkingpos.service;

import vincentlow.parkee.parkingpos.model.entity.Voucher;

public interface VoucherService {

  Voucher findValidVoucherById(String id);
}
