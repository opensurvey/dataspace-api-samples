<?php


const SECRET_KEY = "SECRET_FROM_DATASPACE";
const MAC_ALGORITHM = "sha256";
const SERIAL_STORE_UID_TEMPLATE = "%s?store=%s&uid=%s";


function base64url_encode($data) {
    return rtrim(strtr(base64_encode($data), '+/', '-_'), '=');
}


function sign($message) {
    try {
        $mac = hash_hmac(MAC_ALGORITHM, $message, SECRET_KEY, true);
        return base64url_encode($mac);
    } catch (Exception $e) {
        error_log($e);
        return "";
    }
}


function signForLink($serial, $uid, $name) {
    $message = sprintf(SERIAL_STORE_UID_TEMPLATE, $serial, $name, $uid);
    $signature = sign($message);
    return substr($signature, 0, 8);
}


// Answer Link Sample hmac XUVJFZA_
printf("Answer Link Sample hmac %s\n", signForLink("aLBNYVAk1Ku", "TEST_UID", "gangnam-store"));


?>





