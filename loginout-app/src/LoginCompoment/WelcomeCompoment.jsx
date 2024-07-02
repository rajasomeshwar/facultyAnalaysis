import { useParams } from "react-router-dom";

export default function WelcomeCompoment() {
  const { username } = useParams();

  return (
    <>
      <h1>Wecome : {username}</h1>
    </>
  );
}
