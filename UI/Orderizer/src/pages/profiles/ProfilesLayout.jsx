import { Link, Outlet } from "react-router-dom";

export default function ProfilesLayout() {
  return (
    <div className="flex flex-col px-16">
      <div
        className="w-full h-8 flex justify-center items-center"
        style={{ backgroundColor: "#1f2937" }}
      >
        <Link
          to=""
          className="w-1/2 h-full flex justify-center items-center hover:bg-gray-600"
        >
          <p style={{ color: "#5cab7d" }}>Profiles</p>
        </Link>
        <Link
          to="authorities"
          className="w-1/2 h-full flex justify-center items-center hover:bg-gray-600"
        >
          <p style={{ color: "#5cab7d" }}>Authorities</p>
        </Link>
      </div>
      <div className="flex-1">
        <Outlet />
      </div>
    </div>
  );
}
