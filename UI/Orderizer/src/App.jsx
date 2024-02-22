import { useState } from "react";
import DashboardIcon from "./icons/DashboardIcon.jsx";
import ExportIcon from "./icons/ExportIcon.jsx";
import LogoutIcon from "./icons/LogoutIcon.jsx";
import MaxIcon from "./icons/MaxIcon.jsx";
import MinIcon from "./icons/MinIcon.jsx";
import MonitorIcon from "./icons/MonitorIcon.jsx";
import NotificationsIcon from "./icons/NotificationsIcon.jsx";
import OrdersIcon from "./icons/OrdersIcon.jsx";
import ProfilesIcon from "./icons/ProfilesIcon.jsx";
import ReportsIcon from "./icons/ReportsIcon.jsx";
import SettingsIcon from "./icons/SettingsIcon.jsx";
import UsersIcon from "./icons/UsersIcon.jsx";
export default function App() {
  const color = "#5cab7d";
  const bgColor = "#778da9";
  const [maxSideBar, setMaxSideBar] = useState(true);
  let itemSideClass =
    "px-4 py-2 flex items-center cursor-pointer hover:bg-gray-600";

  function handleMaxSideBar() {
    setMaxSideBar(true);
  }

  function handleMinSideBar() {
    setMaxSideBar(false);
  }
  if (!maxSideBar) {
    itemSideClass += " justify-center";
  }
  return (
    <div
      className="flex h-screen overflow-hidden"
      style={{ backgroundColor: bgColor }}
    >
      <div className="w-fit bg-gray-800">
        <div className="py-8 px-6 text-white font-bold flex justify-center cursor-pointer">
          <img src="/logo.svg" />
        </div>
        <ul className="flex flex-col h-full justify-around">
          <div className="h-fit">
            <li className={itemSideClass}>
              <OrdersIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Orders</p>}
            </li>
            <li className={itemSideClass}>
              <ReportsIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Report</p>}
            </li>
            <li className={itemSideClass}>
              <DashboardIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Dashboard</p>}
            </li>
            <li className={itemSideClass}>
              <ExportIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Export</p>}
            </li>
            <li className={itemSideClass}>
              <NotificationsIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Notifications</p>}
            </li>
            <li className={itemSideClass}>
              <UsersIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Users</p>}
            </li>
            <li className={itemSideClass}>
              <ProfilesIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Profiles</p>}
            </li>
            <li className={itemSideClass}>
              <SettingsIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Settings</p>}
            </li>
            <li className={itemSideClass}>
              <MonitorIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Monitor</p>}
            </li>
            <li className={itemSideClass}>
              <LogoutIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Logout</p>}
            </li>
          </div>
          <div className="h-fit mb-10">
            {!maxSideBar && (
              <li
                className={`${itemSideClass} justify-center `}
                onClick={handleMaxSideBar}
              >
                <MaxIcon className="mr-2" color={color} />
              </li>
            )}
            {maxSideBar && (
              <li
                className={`${itemSideClass} justify-center`}
                onClick={handleMinSideBar}
              >
                <MinIcon className="mr-2" color={color} />
              </li>
            )}
          </div>
        </ul>
      </div>
      <div className="flex-1 p-10"></div>
    </div>
  );
}
