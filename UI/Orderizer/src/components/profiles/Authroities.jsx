import Group from "./Group.jsx";
export default function Authorities({ authorities }) {
  return (
    <div className="flex w-full mt-10 justify-center">
      {Object.keys(authorities).map((groupName) => (
        <div>
          <Group
            className="flex flex-col  px-2 py-10 flex-1"
            key={groupName}
            groupName={groupName}
            authorities={authorities[groupName]}
          />
        </div>
      ))}
    </div>
  );
}
