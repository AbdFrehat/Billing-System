import { QueryClient } from "@tanstack/react-query";

export const queryClient = new QueryClient();

export async function fetchAuthorities({ signal }) {
  let url =
    "http://localhost:7042/selling/auth/profiles/manager/v1/authorities";

  const response = await fetch(url, { signal: signal });

  if (!response.ok) {
    const error = new Error("Failed to fetch authorities");
    error.code = response.code;
    error.info = await response.json();
    throw error;
  }

  const { authorities } = await response.json();
  return authorities;
}
