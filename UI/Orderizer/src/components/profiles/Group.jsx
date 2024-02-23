import Authority from "./Authority";

export default function Group({ groupName, authorities, ...props }) {
  return (
    <div {...props}>
      <p className="bg-gray-800 px-2 h-20 text-center flex justify-center w-full">
        <span className="h-full flex items-center">{groupName}</span>
      </p>
      <div className="flex flex-col items-center">
        {authorities.map((authority) => (
          <Authority
            key={authority.name}
            authorityName={authority.name}
            className="mt-2 p-2 bg-gray-800 h-fit w-full flex justify-center"
          />
        ))}
      </div>
    </div>
  );
}
