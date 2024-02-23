import Logo from "../components/Logo";

export default function HomePage() {
  return (
    <div className="h-full w-full flex flex-col items-center justify-center">
      <Logo className="w-96 h-96" />
      <h1
        className="text-5xl py-16 font-bold tracking-wide"
        style={{ color: "#1f2937" }}
      >
        Orderizer
      </h1>
    </div>
  );
}
