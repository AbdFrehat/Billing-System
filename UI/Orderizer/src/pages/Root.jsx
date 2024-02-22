import { Outlet } from "react-router-dom";
import Sidebar from "../components/Sidebar";
export default function RootLayout() {
  const color = "#5cab7d";
  const bgColor = "#3a5a40";
  const sideBgColor = "#1f2937";
  return (
    <div
      className="flex h-screen overflow-hidden"
      style={{ backgroundColor: bgColor }}
    >
      <Sidebar color={color} bgColor={sideBgColor} />
      <main className="flex-1">
        <Outlet />
      </main>
    </div>
  );
}
