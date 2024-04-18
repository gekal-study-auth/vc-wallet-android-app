package org.idp.wallet.verifiable_credentials_library.mock

import org.idp.wallet.verifiable_credentials_library.basic.resource.ResourceReader

class MockAssetsReader: ResourceReader {
    override fun read(fileName: String): String {
        return """
            {
              "issuer": "http://localhost:8080/123",
              "authorization_endpoint": "openid4vp:",
              "token_endpoint": "http://localhost:8080/123/api/v1/tokens",
              "token_endpoint_auth_methods_supported": [
                "client_secret_post",
                "client_secret_basic",
                "client_secret_jwt",
                "private_key_jwt",
                "tls_client_auth",
                "self_signed_tls_client_auth"
              ],
              "token_endpoint_auth_signing_alg_values_supported": [
                "RS256",
                "ES256"
              ],
              "userinfo_endpoint": "http://localhost:8080/123/api/v1/userinfo",
              "jwks_uri": "http://localhost:8080/123/api/v1/jwks",
              "jwks": "{\n    \"keys\": [\n        {\n            \"kty\": \"EC\",\n            \"d\": \"yIWDrlhnCy3yL9xLuqZGOBFFq4PWGsCeM7Sc_lfeaQQ\",\n            \"use\": \"sig\",\n            \"crv\": \"P-256\",\n            \"kid\": \"access_token\",\n            \"x\": \"iWJINqt0ySv3kVEvlHbvNkPKY2pPSf1cG1PSx3tRfw0\",\n            \"y\": \"rW1FdfXK5AQcv-Go6Xho0CR5AbLai7Gp9IdLTIXTSIQ\",\n            \"alg\": \"ES256\"\n        }\n,{\n    \"kty\": \"EC\",\n    \"d\": \"HrgT4zqM2BvrlwUWagyeNnZ40nZ7rTY4gYG9k99oGJg\",\n    \"use\": \"enc\",\n    \"crv\": \"P-256\",\n    \"kid\": \"request_enc_key\",\n    \"x\": \"PM6be42POiKdNzRKGeZ1Gia8908XfmSSbS4cwPasWTo\",\n    \"y\": \"wksaan9a4h3L8R1UMmvc9w6rPB_F07IA-VHx7n7Add4\",\n    \"alg\": \"ECDH-ES\"\n}]\n}",
              "grant_types_supported": [
                "authorization_code",
                "refresh_token",
                "password",
                "client_credentials",
                "urn:openid:params:grant-type:ciba"
              ],
              "scopes_supported": [
                "openid",
                "profile",
                "email",
                "address",
                "phone",
                "offline_access",
                "account",
                "transfers",
                "read",
                "write"
              ],
              "response_types_supported": [
                "code",
                "token",
                "id_token",
                "code token",
                "code token id_token",
                "token id_token",
                "code id_token",
                "none",
                "vp_token",
                "vp_token id_token"
              ],
              "acr_values_supported": [
                "urn:mace:incommon:iap:silver",
                "urn:mace:incommon:iap:bronze"
              ],
              "subject_types_supported": [
                "public",
                "pairwise"
              ],
              "userinfo_signing_alg_values_supported": [
                "RS256",
                "ES256",
                "HS256"
              ],
              "userinfo_encryption_alg_values_supported": [
                "RSA1_5",
                "A128KW"
              ],
              "userinfo_encryption_enc_values_supported": [
                "A128CBC-HS256",
                "A128GCM"
              ],
              "id_token_signing_alg_values_supported": [
                "RS256",
                "ES256",
                "HS256"
              ],
              "id_token_encryption_alg_values_supported": [
                "RSA1_5",
                "A128KW"
              ],
              "id_token_encryption_enc_values_supported": [
                "A128CBC-HS256",
                "A128GCM"
              ],
              "request_object_signing_alg_values_supported": [
                "none",
                "RS256",
                "ES256"
              ],
              "display_values_supported": [
                "page",
                "popup"
              ],
              "claim_types_supported": [
                "normal",
                "distributed"
              ],
              "claims_supported": [
                "sub",
                "iss",
                "auth_time",
                "acr",
                "name",
                "given_name",
                "family_name",
                "nickname",
                "profile",
                "picture",
                "website",
                "email",
                "email_verified",
                "locale",
                "zoneinfo",
                "birthdate",
                "gender",
                "preferred_username",
                "middle_name",
                "updated_at",
                "address",
                "phone_number",
                "phone_number_verified"
              ],
              "claims_parameter_supported": true,
              "service_documentation": "http://server.example.com/connect/service_documentation.html",
              "ui_locales_supported": [
                "en-US",
                "en-GB",
                "en-CA",
                "fr-FR",
                "fr-CA"
              ],
              "token_introspection_endpoint": "http://localhost:8080/123/api/v1/tokens/introspection",
              "token_revocation_endpoint": "http://localhost:8080/123/api/v1/tokens/revocation",
              "backchannel_token_delivery_modes_supported": [
                "poll",
                "ping",
                "push"
              ],
              "backchannel_authentication_endpoint": "http://localhost:8080/123/api/v1/backchannel/authentications",
              "backchannel_authentication_request_signing_alg_values_supported": [
                "RS256",
                "ES256"
              ],
              "backchannel_user_code_parameter_supported": true,
              "authorization_details_types_supported":[
                "payment_initiation",
                "account_information",
                "openid_credential"
              ],
              "authorization_signing_alg_values_supported": [
                "RS256",
                "ES256",
                "HS256"
              ],
              "authorization_encryption_alg_values_supported": [
                "RSA1_5",
                "A128KW"
              ],
              "authorization_encryption_enc_values_supported": [
                "A128CBC-HS256",
                "A128GCM"
              ],
              "tls_client_certificate_bound_access_tokens": true,
              "presentation_definition_uri_supported": true,
              "vp_formats_supported": {
                "jwt_vc_json": {
                  "alg_values_supported": [
                    "ES256K",
                    "ES384"
                  ]
                },
                "jwt_vp_json": {
                  "alg_values_supported": [
                    "ES256K",
                    "EdDSA"
                  ]
                }
              },
              "client_id_schemes_supported": true,
              "token_signed_key_id": "access_token",
              "id_token_signed_key_id": "access_token",
              "access_token_duration": 3600,
              "id_token_duration": 3600,
              "id_token_strict_mode": true,
              "default_max_age": 86400,
              "fapi_baseline_scopes" : [
                "read"
              ],
              "fapi_advance_scopes" : [
                "write"
              ]
            }
        """.trimIndent()
    }
}