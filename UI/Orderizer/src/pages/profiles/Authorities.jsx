import { useQuery } from "@tanstack/react-query";
import Authorities from "../../components/profiles/Authroities";
import {
  fetchAuthorities,
  queryClient,
} from "../../util/profiles-authorities-http";

export default function AuthoritiesPage() {
  const { data, isPending, isError, error } = useQuery({
    queryKey: ["authorities"],
    queryFn: ({ signal }) => fetchAuthorities({ signal }),
    staleTime: 10000,
  });

  return <Authorities authorities={data} />;
}

export function loader() {
  return queryClient.fetchQuery({
    queryKey: ["authorities"],
    queryFn: ({ signal }) => fetchAuthorities({ signal }),
  });
}
