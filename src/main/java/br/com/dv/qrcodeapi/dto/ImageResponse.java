package br.com.dv.qrcodeapi.dto;

import org.springframework.http.MediaType;

public record ImageResponse(byte[] imageData, MediaType mediaType) {
}
