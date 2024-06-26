import { useRef } from "react";
import useScript from "../../hooks/useScript";
export default function GoogleLogin({
  onGoogleSignIn = () => {},
  text = "signing_with",
}) {
  const googleSignInButton = useRef(null);

  useScript("https://accounts.google.com/gsi/client", () => {
    // https://developers.google.com/identity/gsi/web/reference/js-reference#google.accounts.id.initialize
    window.google.accounts.id.initialize({
      client_id: process.env.REACT_APP_GOOGLE_CLIENT_ID,
      callback: onGoogleSignIn,
    });
    // https://developers.google.com/identity/gsi/web/reference/js-reference#google.accounts.id.renderButton
    window.google.accounts.id.renderButton(
      googleSignInButton.current,
      { theme: "outline", size: "large", text, width: "100%" } // customization attributes
    );
  });

  return <div ref={googleSignInButton}></div>;
}
