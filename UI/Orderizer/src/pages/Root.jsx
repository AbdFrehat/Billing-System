import { Outlet } from "react-router-dom";
import Sidebar from "../components/Sidebar";
export default function RootLayout() {
  const color = "#5cab7d";
  const bgColor = "#3a5a40";
  const sideBgColor = "#1f2937";
  return (
    <div className="flex h-screen" style={{ backgroundColor: bgColor }}>
      <Sidebar color={color} bgColor={sideBgColor} />
      <main className="ml-10 flex-1 h-full overflow-y-auto">
        <Outlet />
      </main>
    </div>
  );
}
