import { useState } from "react";
import { Link } from "react-router-dom";
import DashboardIcon from "../icons/DashboardIcon.jsx";
import ExportIcon from "../icons/ExportIcon.jsx";
import LogoutIcon from "../icons/LogoutIcon.jsx";
import MaxIcon from "../icons/MaxIcon.jsx";
import MinIcon from "../icons/MinIcon.jsx";
import MonitorIcon from "../icons/MonitorIcon.jsx";
import NotificationsIcon from "../icons/NotificationsIcon.jsx";
import OrdersIcon from "../icons/OrdersIcon.jsx";
import ProfilesIcon from "../icons/ProfilesIcon.jsx";
import ReportsIcon from "../icons/ReportsIcon.jsx";
import SettingsIcon from "../icons/SettingsIcon.jsx";
import UsersIcon from "../icons/UsersIcon.jsx";
import Logo from "./Logo.jsx";
export default function Sidebar({ color, bgColor }) {
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
    <div className="w-fit" style={{ backgroundColor: bgColor }}>
      <div className="py-8 px-6 text-white font-bold flex justify-center cursor-pointer">
        <Link to="/">
          <Logo />
        </Link>
      </div>
      <ul className="flex flex-col h-full justify-around">
        <div className="h-fit">
          <li>
            <Link to="orders" className={itemSideClass}>
              <OrdersIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Orders</p>}
            </Link>
          </li>
          <li>
            <Link to="reports" className={itemSideClass}>
              <ReportsIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Report</p>}
            </Link>
          </li>
          <li>
            <Link to="dashboard" className={itemSideClass}>
              <DashboardIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Dashboard</p>}
            </Link>
          </li>
          <li>
            <Link to="export" className={itemSideClass}>
              <ExportIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Export</p>}
            </Link>
          </li>
          <li>
            <Link to="notifications" className={itemSideClass}>
              <NotificationsIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Notifications</p>}
            </Link>
          </li>
          <li>
            <Link to="users" className={itemSideClass}>
              <UsersIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Users</p>}
            </Link>
          </li>
          <li>
            <Link to="profiles" className={itemSideClass}>
              <ProfilesIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Profiles</p>}
            </Link>
          </li>
          <li>
            <Link to="settings" className={itemSideClass}>
              <SettingsIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Settings</p>}
            </Link>
          </li>
          <li>
            <Link to="monitor" className={itemSideClass}>
              <MonitorIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Monitor</p>}
            </Link>
          </li>
          <li>
            <Link to="logout" className={itemSideClass}>
              <LogoutIcon className="mr-2" color={color} />
              {maxSideBar && <p className="text-white">Logout</p>}
            </Link>
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
  );
}
