package br.com.dv.qrcodeapi.service;

import br.com.dv.qrcodeapi.dto.ImageResponse;

public interface QrCodeService {

    ImageResponse generateQrCode(String content, int size, String format);

}
