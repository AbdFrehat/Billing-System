import { QueryClientProvider } from "@tanstack/react-query";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import DashboardPage from "./pages/Dashboard.jsx";
import ExportPage from "./pages/Export.jsx";
import HomePage from "./pages/Home.jsx";
import MonitorPage from "./pages/Monitor.jsx";
import NotificationsPage from "./pages/Notifications.jsx";
import OrdersPage from "./pages/Orders.jsx";
import ReportsPage from "./pages/Reports.jsx";
import RootLayout from "./pages/Root.jsx";
import SettingsPage from "./pages/Settings.jsx";
import UsersPage from "./pages/Users.jsx";
import AuthoritiesPage, {
  loader as authoritiesLoader,
} from "./pages/profiles/Authorities.jsx";
import ProfilesPage from "./pages/profiles/Profiles.jsx";
import ProfilesLayout from "./pages/profiles/ProfilesLayout.jsx";
import { queryClient } from "./util/profiles-authorities-http.js";
const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    children: [
      {
        index: true,
        element: <HomePage />,
      },
      {
        path: "orders",
        element: <OrdersPage />,
      },
      {
        path: "reports",
        element: <ReportsPage />,
      },
      {
        path: "dashboard",
        element: <DashboardPage />,
      },
      {
        path: "export",
        element: <ExportPage />,
      },
      {
        path: "notifications",
        element: <NotificationsPage />,
      },
      {
        path: "monitor",
        element: <MonitorPage />,
      },
      {
        path: "profiles",
        element: <ProfilesLayout />,
        children: [
          {
            index: true,
            element: <ProfilesPage />,
          },
          {
            path: "authorities",
            element: <AuthoritiesPage />,
            loader: authoritiesLoader,
          },
        ],
      },
      {
        path: "settings",
        element: <SettingsPage />,
      },
      {
        path: "users",
        element: <UsersPage />,
      },
      {
        path: "logout",
      },
    ],
  },
]);
export default function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  );
}
