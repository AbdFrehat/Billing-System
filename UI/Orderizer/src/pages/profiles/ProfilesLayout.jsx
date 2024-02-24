import { Link, Outlet } from "react-router-dom";

export default function ProfilesLayout() {
  return (
    <div className="flex flex-col px-16">
      <div className="w-full h-8 flex justify-around items-center">
        <Link
          to=""
          className="w-2/5 h-full flex justify-center items-center bg-mainColor hover:bg-gray-600"
        >
          <p style={{ color: "#5cab7d" }}>Profiles</p>
        </Link>
        <Link
          to="authorities"
          className="w-2/5 h-full flex justify-center items-center bg-mainColor hover:bg-gray-600"
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
