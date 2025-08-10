package com.nlhstore.service;

import com.nimbusds.jose.JOSEException;
import com.nlhstore.dto.request.AuthenticationRequest;
import com.nlhstore.dto.request.IntrospectRequest;
import com.nlhstore.dto.response.AuthenticationResponse;
import com.nlhstore.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface IAuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
